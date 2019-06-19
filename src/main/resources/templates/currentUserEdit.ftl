<#import "parts/common.ftl" as c>
    <#import "parts/login.ftl" as l>
<@c.page>
    <h3>After the change, you need to reconnect to the site</h3>
        <@l.login "/userInfo/currentUserEdit"></@l.login>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
</@c.page>