
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Area1")
public class Area1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String lStr = request.getParameter("lenght");
        String wStr = request.getParameter("width");
        
        try {
            double length = Double.parseDouble(lStr);
            double width = Double.parseDouble(wStr);
            double area = length * width;

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Result | MATHS HELP</title>");
            out.println("<link rel='stylesheet' href='style.css?v=1.2'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header><h1>MATHS HELP</h1></header>");
            out.println("<main>");
            out.println("<div class='card'>");
            out.println("<h2>Calculation Result</h2>");
            out.println("<div class='result-box'>");
            out.println("<p class='result-label'>Total Area</p>");
            out.println("<div class='result-value'>" + String.format("%.2f", area) + "</div>");
            out.println("</div>");
            out.println("<button onclick='window.history.back()'>Calculate Again</button>");
            out.println("</div>");
            out.println("</main>");
            out.println("<footer><p>&copy; 2024 Professional Calculation Tools</p></footer>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body><p>Error: Invalid Parameters</p><button onclick='window.history.back()'>Go Back</button></body></html>");
        }
    }
}
