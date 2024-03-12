
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GerenciadorTarefasServlet")
public class GerenciadorTarefasServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static class Tarefa {

        private String titulo;
        private String descricao;
        private String dueDate;

        public Tarefa(String titulo, String descricao, String dueDate) {
            this.titulo = titulo;
            this.descricao = descricao;
            this.dueDate = dueDate;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getDueDate() {
            return dueDate;
        }
    }

    private List<Tarefa> tarefas = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtenha os parâmetros do formulário
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String dueDate = request.getParameter("dueDate");

        // Validação simples
        if (titulo != null && !titulo.isEmpty() && descricao != null && !descricao.isEmpty() && dueDate != null && !dueDate.isEmpty()) {
            // Salve os dados em uma lista (simulando um banco de dados)
            Tarefa tarefa = new Tarefa(titulo, descricao, dueDate);
            tarefas.add(tarefa);

            // Envie uma resposta ao cliente
            response.getWriter().println("Tarefa adicionada com sucesso!");
        } else {
            response.getWriter().println("Por favor, preencha todos os campos.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica se o parâmetro "visualizar" está presente
        String visualizar = request.getParameter("visualizar");

        if ("true".equals(visualizar)) {
            // Exibe os dados salvos
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<h2>Tarefas Salvas:</h2>");

                if (tarefas.isEmpty()) {
                    out.println("<p>Nenhuma tarefa foi adicionada ainda.</p>");
                } else {
                    out.println("<ul>");
                    for (Tarefa tarefa : tarefas) {
                        out.println("<li><strong>" + tarefa.getTitulo() + "</strong><br>" + tarefa.getDescricao()
                                + "<br>Due Date: " + tarefa.getDueDate() + "</li>");
                    }
                    out.println("</ul>");
                }
            }
        } else {
            processRequest(request, response);
        }
    }
}
