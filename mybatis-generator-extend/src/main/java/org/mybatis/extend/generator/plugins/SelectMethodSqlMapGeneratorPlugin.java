package org.mybatis.extend.generator.plugins;

import org.mybatis.extend.generator.generat.SelectMethodGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/2/13.
 */
public class SelectMethodSqlMapGeneratorPlugin extends PluginAdapter {

    public boolean validate(List<String> list) {
        return true;
    }

    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        SelectMethodGenerator generator = new SelectMethodGenerator(parentElement, introspectedTable);
        generator.addModelWhereCase();
        generator.addSelectAll();
        generator.addSelect();
        generator.addSelectOne();
        generator.addSelectPageList();
        generator.addSelectCount();

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

}
