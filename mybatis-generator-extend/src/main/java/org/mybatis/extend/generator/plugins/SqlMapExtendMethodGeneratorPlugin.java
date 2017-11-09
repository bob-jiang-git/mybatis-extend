package org.mybatis.extend.generator.plugins;

import org.mybatis.extend.generator.generat.SqlMapExtendMethodGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/2/13.
 */
public class SqlMapExtendMethodGeneratorPlugin extends PluginAdapter {

    public boolean validate(List<String> list) {
        return true;
    }

    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        SqlMapExtendMethodGenerator generator = new SqlMapExtendMethodGenerator(parentElement, introspectedTable);
        generator.addModelWhereCase();
        generator.addSelectAll();
        generator.addSelect();
        generator.addSelectOne();
        generator.addSelectPageList();
        generator.addSelectCount();

        generator.addBatchInsert();
        generator.addDeleteByIds();

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

}
