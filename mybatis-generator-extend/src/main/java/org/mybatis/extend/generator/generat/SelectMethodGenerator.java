package org.mybatis.extend.generator.generat;

import org.mybatis.extend.generator.element.GenericTextElement;
import org.mybatis.extend.generator.element.GenericXmlElement;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/11/3.
 */
public class SelectMethodGenerator {

    public static final String WHERE_CLAUSE_ID = "model_where_clause";

    private XmlElement parentElement;
    private IntrospectedTable introspectedTable;

    public SelectMethodGenerator(XmlElement parentElement, IntrospectedTable introspectedTable) {
        this.parentElement = parentElement;
        this.introspectedTable = introspectedTable;
    }

    public XmlElement buildSelectElement(String selectId) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        XmlElement element = new XmlElement("select");
        element.addAttribute(new Attribute("id", selectId));

        boolean hasBLOBColumns = introspectedTable.getBLOBColumns().size() > 0;

        element.addAttribute(new Attribute("resultMap", hasBLOBColumns ? introspectedTable.getResultMapWithBLOBsId() : introspectedTable.getBaseResultMapId()));
        element.addElement(new GenericTextElement("select"));

        String colum = "<include refid=\"" + introspectedTable.getBaseColumnListId() + "\" />";
        if (hasBLOBColumns) {
            colum += ", <include refid=\"" + introspectedTable.getBlobColumnListId()+ "\" />";
        }
        element.addElement(new GenericTextElement(colum));
        element.addElement(new GenericTextElement("from " + tableName));
        return element;
    }

    private void buildWhereCase(XmlElement element) {
        element.addElement(new GenericTextElement("<include refid=\"" + WHERE_CLAUSE_ID + "\"/>"));
        parentElement.addElement(element);
    }

    public void addModelWhereCase() {

        GenericXmlElement whereCase = new GenericXmlElement("sql");
        whereCase.addAttribute(new Attribute("id", WHERE_CLAUSE_ID));

        GenericXmlElement whereElement = new GenericXmlElement("where");
        whereCase.addElement(whereElement);

        String alias = introspectedTable.getTableConfiguration().getAlias();
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columns) {
            GenericXmlElement ifElement = new GenericXmlElement("if");
            String test = "model." + column.getJavaProperty() + " != null";
            ifElement.addAttribute(new Attribute("test", test));

            String cases = "and " + alias + "." + column.getActualColumnName() + " = #{model." + column.getJavaProperty() + "}";
            GenericTextElement caseElement = new GenericTextElement(cases);
            ifElement.addElement(caseElement);
            whereElement.addElement(ifElement);
        }
        parentElement.addElement(whereCase);
        parentElement.addElement(new TextElement(""));
    }

    public void addSelectAll() {
        XmlElement element = buildSelectElement("selectAll");
        parentElement.addElement(element);
        parentElement.addElement(new TextElement(""));
    }

    public void addSelect() {
        XmlElement element = buildSelectElement("select");
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        buildWhereCase(element);
        parentElement.addElement(new TextElement(""));
    }

    public void addSelectOne() {
        XmlElement element = buildSelectElement("selectOne");
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        buildWhereCase(element);
        element.addElement(new GenericTextElement("limit 1"));
        parentElement.addElement(new TextElement(""));
    }

    public void addSelectPageList() {
        XmlElement element = buildSelectElement("selectPageList");
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        buildWhereCase(element);
        parentElement.addElement(new TextElement(""));
    }

    public void addSelectCount() {
        XmlElement element = new XmlElement("select");
        element.addAttribute(new Attribute("id", "selectCount"));
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        element.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        element.addAttribute(new Attribute("resultType", "java.lang.Integer"));
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        element.addElement(new GenericTextElement("select count(1) from " + tableName));
        buildWhereCase(element);
        parentElement.addElement(new TextElement(""));
    }
}
