<#import "parts/common.ftl" as c>
<@c.page>
    <div>
        <span><a href="/user">Add user</a></span>
    </div>
    <h2>Пожалуйста загрузите файл с выручкой</h2>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="file" name="file" />
        <button type="submit">Загрузить</button>
    </form>
    <div>
    ${message?if_exists}
    </div>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Sells</th>
        </tr>
        </thead>
        <tbody>
        <#if list??>
            <#list list as key, value>
                <tr>
                    <td>${key}</td>
                    <td>${value}</td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@c.page>