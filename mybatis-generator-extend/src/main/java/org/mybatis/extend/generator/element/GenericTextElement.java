package org.mybatis.extend.generator.element;

import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.TextElement;

/**
 * Created by Bob Jiang on 2017/11/2.
 */
public class GenericTextElement extends TextElement {

    /**
     * @param content
     */
    public GenericTextElement(String content) {
        super(content);
    }

    @Override
    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append(getContent());
        return sb.toString();
    }
}
