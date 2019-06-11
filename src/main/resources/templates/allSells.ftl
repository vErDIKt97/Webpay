<#import "parts/common.ftl" as c>
<@c.page>
    <span><a href="/user">User List</a></span>
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