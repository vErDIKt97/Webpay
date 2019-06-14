<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
 <@l.loguot />
     <span><a href="/user">User List</a> </span>
     <header class="header_wind">
         <h1>Все что тебе нужно знать!</h1>
     </header>
    <div>
        <table>
            <thead>
            <tr>
                <th>UserName</th>
                <th>Password</th>
                <th>Name</th>
                <th>Sername</th>
                <th>Sells</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.name!}</td>
                    <td>${user.surname!}</td>
                    <td>${user.sells!}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div>
        <p><a href="/userInfo/currentUserEdit">Change name and password</a></p>
    </div>
</@c.page>