<#import "parts/common.ftl" as c>
    <#import "parts/login.ftl" as l>
<@c.page>
    <@l.loguot/>
    User editor

    <form action="/user" method="post">
        <table>
            <thead>
            <tr>
                <th>Username</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><label>
                        <input type="text" name="username" value="${user.username}" />
                    </label></td>
                <td><label>
                        <input type="text" name="name" value="${user.name!}">
                    </label></td>
                <td><label>
                        <input type="text" name="surname" value="${user.surname!}">
                    </label></td>
                <td><label>
                        <input type="text" name="password" value="${user.password}"
                    </label></td>
                <td>
                    <#list roles as role>
                        <div>
                            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} />${role}</label>
                        </div>
                    </#list>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" value="${user.id}" name="userId" />
        <input type="hidden" value="${_csrf.token}" name="_csrf" />
        <button type="submit">Save</button>
    </form>
</@c.page>