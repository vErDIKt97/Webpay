<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/userInfo/currentUserEdit" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">New user name : </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User Name" /> </label>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">New password : </label>
            <div class="col-sm-6">
                <input type="text" name="password"  class="form-control" placeholder="Password"/> </label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div> <button type="submit" class="btn btn-primary">Submit</button></div>
    </form>
</@c.page>