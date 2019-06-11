<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <@l.loguot/>
    <div>
        <span><a href="/registration">Add user</a></span>
    </div>
    <div>
        <span><a href="/upload">User List</a> </span>
    </div>
    <h2>List of users</h2>
    <table>
        <thead>
        <tr>
            <th>UserName</th>
            <th>Name</th>
            <th>SoName</th>
            <th>Sells</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>${user.name!}</td>
            <td>${user.soname!}</td>
            <td>${user.sells!}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a> </td>
        </tr>
    </#list>
        </tbody>
    </table>
</@c.page>