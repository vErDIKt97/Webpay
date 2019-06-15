<#import "parts/common.ftl" as c>
<@c.page>
    <h2>List of users</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">UserName</th>
            <th scope="col">Password</th>
            <th scope="col">Name</th>
            <th scope="col">Sername</th>
            <th scope="col">Sells</th>
            <th scope="col">Role</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.name!}</td>
            <td>${user.surname!}</td>
            <td>${user.sells!}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a> </td>
            <td><a href="/user/del${user.id}">delete</a></td>
        </tr>
    </#list>
        </tbody>
    </table>
    ${message!}
</@c.page>