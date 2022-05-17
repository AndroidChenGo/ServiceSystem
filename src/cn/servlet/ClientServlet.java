package cn.servlet;

import cn.application.ClientService;
import cn.domain.model.Client;
import cn.infrastructure.util.StringUtil;
import cn.infrastructure.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClientService clientService = new ClientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=utf-8");
        HttpSession session = request.getSession();
        Object currentUserType = session.getAttribute("currentUserType");
        String action = request.getParameter("action");
        if("list".equals(action)) {
            //1 通过ClientService查询全部客户
            List<Client> clients = clientService.queryClients();
            //2 把全部客户保存到Request域中
            request.setAttribute("userList", clients);
            //3、请求转发到client.jsp页面
            request.setAttribute("mainPage", "page/admin/client.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("preSave".equals(action)){
            String userId = request.getParameter("userId");
            if(StringUtil.isNotEmpty(userId)){
                Client client = clientService.queryClientById(Integer.parseInt(userId));
                request.setAttribute("user", client);
            }
            request.setAttribute("mainPage", "page/admin/clientSave.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("save".equals(action)){
            Client client = WebUtils.copyParamToBean(request.getParameterMap(),new Client());
            if(clientService.saveClient(client)){
                request.getRequestDispatcher("client?action=list").forward(request, response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                request.setAttribute("error", "id is repeated");
                request.setAttribute("user", client);
                request.setAttribute("mainPage", "page/admin/clientSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            };
        }else if("search".equals(action)){
            String searchText = request.getParameter("searchText");
            String searchType = request.getParameter("searchType");
            List<Client> clients = new ArrayList<>();
            if("name".equals(searchType)) {
                clients = clientService.queryClientByName(searchText);
            } else if("id".equals(searchType)) {
                clients.add(clientService.queryClientById(Integer.parseInt(searchText)));
            } else if("type".equals(searchType)) {
                clients = clientService.queryClientByType(searchText);
            } else if("introduction".equals(searchType)) {
                clients = clientService.queryClientByIntroduction(searchText);
            }
            request.setAttribute("searchText", searchText);
            request.setAttribute("searchType", searchType);
            //2 把全部客户保存到Request域中
            request.setAttribute("userList", clients);
            //3、请求转发到client.jsp页面
            request.setAttribute("mainPage", "page/admin/client.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("delete".equals(action)){
            int id = WebUtils.parseInt(request.getParameter("userId"), 0);
            clientService.deleteClientById(id);
            request.getRequestDispatcher("client?action=list").forward(request, response);
        }

    }

}
