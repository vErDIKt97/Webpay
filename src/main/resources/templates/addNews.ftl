<#import "parts/common.ftl" as c>
<@c.page>
    <p><a href="/news/delAll"> Delete all new's</a> </p>
        <div class="form-group mt-3">
            <form action="/news/addNews" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Tag" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Enter news" />
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile" />
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Save message</button>
                </div>
            </form>
        </div>
    <#if messages??>
        <#list messages as message>
            <div class="container my-3">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <div class="card-title">#${message.tag}</div>
                                <div class="card-text">
                                    <div class="my-3" data-id="${message.id}">
                                        <#if message.filename??>
                                            <img src="/img/${message.filename}" class="card-img-top" />
                                        </#if>
                                        <div class="m-2">
                                            <span>${message.text}</span><br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <#else> No news
    </#if>
</@c.page>