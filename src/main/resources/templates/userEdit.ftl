<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/user" method="post">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Password</th>
                <th scope="col">Role</th>
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
                        <input type="text" name="password" placeholder="Blank if you don't change">
                    </label></td>
                <td>
                    <#list roles as role>
                        <div>
                            <label><input type="checkbox" class="form-check-input" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} />${role}</label>
                        </div>
                    </#list>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" value="${user.id}" name="userId" />
        <input type="hidden" value="${_csrf.token}" name="_csrf" />
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>