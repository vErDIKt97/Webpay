<#import "parts/common.ftl" as c>
<@c.page>
    <div>
        <span><a href="/user">User List</a></span>
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
</@c.page>