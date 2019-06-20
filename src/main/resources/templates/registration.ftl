<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

    ${message!}
    <@l.login "/registration">
        <h2><div class="mb-1">Add new user</div></h2>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" placeholder="Name" /> </label>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Surname</label>
            <div class="col-sm-6">
                <input type="text" name="surname"  class="form-control" placeholder="Surname"/> </label>
            </div>
        </div>
    </@l.login>
</@c.page>