<#import "parts/common.ftl" as c>
<@c.page>
    <h2>Please upload file with sells</h2>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="custom-file">
            <input type="file" name="file" id="customFile"/>
            <label class="custom-file-label" for="customFile">Choose file</label>
        </div>
        <div class="mt-3">
            <button type="submit" class="btn btn-primary">Upload</button>
        </div>
    </form>
    <div>
        ${message?if_exists}
    </div>
</@c.page>