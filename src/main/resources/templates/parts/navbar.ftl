<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">WorkerPayments</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/userInfo">Main</a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/userInfo/currentUserEdit">Edit profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/news">News</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/news/addNews">Edit news</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Add user</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/upload">Upload File sells</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/upload/allSells">All sells</a>
                </li>
            </#if>
        </ul>

        <div class="navbar - text mr-3">${name}</div>
        <#if user??>
            <@l.loguot/>
        </#if>
    </div>
</nav>