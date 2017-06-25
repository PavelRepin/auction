package training.task.model.api;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class StartImageTag extends TagSupport {
    public int doStartTag() throws JspException {
        String pathOfStartImage = "/images/little_hammer.gif";
        String stringForWriter = "<img id=\"startpic\" alt=\"Final Bid.\" src=\"" + pathOfStartImage + "\">";
        try {
            JspWriter out = pageContext.getOut();
            out.write(stringForWriter);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
