<#import "parts/common.ftl" as c>
<@c.page>

     <header class="header_wind">
         <h1>Everything you need to know!</h1>
     </header>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">UserName</th>
                <th scope="col">Password</th>
                <th scope="col">Name</th>
                <th scope="col">Sername</th>
                <th scope="col">Sells</th>
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
</@c.page>