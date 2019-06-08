<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    Add new User
    ${message!}
    <@l.login "/registration">
        <div><label> User FIO : <input type="text" name="fio"/> </label></div>
    </@l.login>
</@c.page>