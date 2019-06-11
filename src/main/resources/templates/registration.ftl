<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    Add new User
    ${message!}
    <@l.login "/registration">
        <div><label> Name : <input type="text" name="name"/> </label></div>
        <div><label> SoName : <input type="text" name="soname"/> </label></div>
    </@l.login>
</@c.page>