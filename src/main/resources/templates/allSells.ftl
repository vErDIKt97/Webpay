<#import "parts/common.ftl" as c>
<@c.page>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Sells</th>
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