package cn.domain.model;

public class Contract {
    private Integer id;
    private String name;
    private String state;
    private String content;
    private Integer clientId;
    private Integer freelancerId;
    private Integer projectId;
    public Contract(Integer clientId, Integer freelancerId, Integer projectId) {
        this.clientId = clientId;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
    }

    public Contract() {
    }

    public Contract(Integer id, String name, String state, String content, Integer clientId, Integer freelancerId, Integer projectId) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.content = content;
        this.clientId = clientId;
        this.freelancerId = freelancerId;
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Integer freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
