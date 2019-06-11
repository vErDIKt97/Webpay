<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <@l.loguot/>
    <div>
        <span><a href="userInfo">Main</a> </span>
    </div>
    <div>
        <span><a href="/registration">Add user</a></span>
    </div>
    <div>
        <span><a href="/upload"> Upload File</a> </span>
    </div>
    <div>
        <span><a href="/upload/allSells">All Sells</a> </span>
    </div>
    <h2>List of users</h2>
    <table>
        <thead>
        <tr>
            <th>UserName</th>
            <th>Password</th>
            <th>Name</th>
            <th>Sername</th>
            <th>Sells</th>
            <th>Role</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.name!}</td>
            <td>${user.surname!}</td>
            <td>${user.sells!} руб.</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a> </td>
            <td><a href="/user/del/${user.id}">delete</a></td>
        </tr>
    </#list>
        </tbody>
    </table>
</@c.page>