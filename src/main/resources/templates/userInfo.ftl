<#import "parts/common.ftl" as c>
<@c.page>

     <header class="header_wind">
         <h2>Everything you need to know!</h2>
     </header>
    <div>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">UserName</th>
                <th scope="col">Name</th>
                <th scope="col">Sername</th>
                <th scope="col">Sells</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${user.username}</td>
                    <td>${user.name!}</td>
                    <td>${user.surname!}</td>
                    <td>${user.sells!}</td>
                </tr>
            </tbody>
        </table>
    </div>
</@c.page>