<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/userInfo/currentUserEdit" method="post">
        <label>Новый логин<input type="text" name="login" value="${login}"></label>
        <label>Новый пароль<input type="text" name="password" value="${password}"></label>
        <input type="hidden" value="${_csrf.token}" name="_csrf" />
        <button type="submit">Сменить логин и пароль</button>
    </form>
</@c.page>