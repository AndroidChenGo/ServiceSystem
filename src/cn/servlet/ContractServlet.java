package cn.servlet;

import cn.application.ContractService;
import cn.domain.model.Contract;
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

public class ContractServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ContractService contractService = new ContractService();

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
            //1 通过ContractService查询全部合同
            List<Contract> contracts = contractService.queryContracts();
            //2 把全部合同保存到Request域中
            request.setAttribute("contractList", contracts);
            //3、请求转发到页面
            request.setAttribute("mainPage", "page/admin/contract.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("preSave".equals(action)){
            String contractId = request.getParameter("contractId");
            if(StringUtil.isNotEmpty(contractId)){
                Contract contract = contractService.queryContractById(Integer.parseInt(contractId));
                request.setAttribute("contract", contract);
            }
            request.setAttribute("mainPage", "page/admin/contractSave.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("save".equals(action)){
            Contract contract = WebUtils.copyParamToBean(request.getParameterMap(),new Contract());
            if(contractService.saveContract(contract)){
                request.getRequestDispatcher("contract?action=list").forward(request, response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                request.setAttribute("error", "please check the three related id");
                request.setAttribute("contract", contract);
                request.setAttribute("mainPage", "page/admin/contractSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            };
        }else if("search".equals(action)){
            String searchText = request.getParameter("searchText");
            String searchType = request.getParameter("searchType");
            List<Contract> contracts = new ArrayList<>();
            if("name".equals(searchType)) {
                contracts = contractService.queryContractByName(searchText);
            } else if("id".equals(searchType)) {
                contracts.add(contractService.queryContractById(Integer.parseInt(searchText)));
            } else if("state".equals(searchType)) {
                contracts = contractService.queryContractByState(searchText);
            }else if("clientId".equals(searchType)) {
                contracts = contractService.queryContractByClientId(Integer.parseInt(searchText));
            }else if("freelancerId".equals(searchType)) {
                contracts = contractService.queryContractByFreelancerId(Integer.parseInt(searchText));
            }else if("projectId".equals(searchType)) {
                contracts = contractService.queryContractByProjectId(Integer.parseInt(searchText));
            }
            request.setAttribute("searchText", searchText);
            request.setAttribute("searchType", searchType);
            //2 把全部合同保存到Request域中
            request.setAttribute("contractList", contracts);
            //3、请求转发到页面
            request.setAttribute("mainPage", "page/admin/contract.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("delete".equals(action)){
            int id = WebUtils.parseInt(request.getParameter("contractId"), 0);
            contractService.deleteContractById(id);
            request.getRequestDispatcher("contract?action=list").forward(request, response);
        }

    }

}
