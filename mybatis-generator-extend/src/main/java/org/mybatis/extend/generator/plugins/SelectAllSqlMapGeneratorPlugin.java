package org.mybatis.extend.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/2/13.
 */
public class SelectAllSqlMapGeneratorPlugin extends PluginAdapter {

    public boolean validate(List<String> list) {
        return true;
    }

    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        XmlElement parentElement = document.getRootElement();

        XmlElement selectAllElement = new XmlElement("select");
        selectAllElement.addAttribute(new Attribute("id", "selectAll"));

        String mapId = introspectedTable.getResultMapWithBLOBsId();
        if (mapId == null || "".equals(mapId)) {
            mapId = introspectedTable.getBaseResultMapId();
        }
        selectAllElement.addAttribute(new Attribute("resultMap", mapId));

        StringBuilder sql = new StringBuilder("select ");
        sql.append("<include refid=\"").append(introspectedTable.getBaseColumnListId()).append("\" />");

        String columnId = introspectedTable.getBlobColumnListId();
        if (columnId != null && !"".equals(columnId)) {
            sql.append(", <include refid=\"").append(columnId).append("\" />");
        }
        sql.append(" from ").append(tableName);
        selectAllElement.addElement(new TextElement(sql.toString()));
        parentElement.addElement(selectAllElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

}
