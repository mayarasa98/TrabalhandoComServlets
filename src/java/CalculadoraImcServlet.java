import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;

@WebServlet(urlPatterns = {"/CalculadoraImcServlet"})
public class CalculadoraImcServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Obtendo os parâmetros do formulário
            double peso = Double.parseDouble(request.getParameter("peso").replace(',','.'));
            double altura = Double.parseDouble(request.getParameter("altura").replace(',','.'));

            // Calculando o IMC
            double imc = calcularIMC(peso, altura);

            // Interpretando o resultado do IMC
            String resultado = interpretarResultadoIMC(imc);

            // Enviando a resposta HTML diretamente do servlet
            out.println("<html>");
            out.println("<head><title>Resultado do IMC</title></head>");
            out.println("<body>");
            out.println("<h2>Resultado do IMC</h2>");
            out.println("<p>O Índice de Massa Corporal (IMC) é: " + imc + "</p>");
            out.println("<p>Resultado: " + resultado + "</p>");
            out.println("</body>");
            out.println("</html>");

        } catch (NumberFormatException e) {
            out.println("Erro: Certifique-se de inserir valores válidos para peso e altura.");
        } finally {
            out.close();
        }
    }

    private static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    private static String interpretarResultadoIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade Grau I";
        } else if (imc < 39.9) {
            return "Obesidade Grau II";
        } else {
            return "Obesidade Grau III";
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        Locale.setDefault(Locale.US);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
