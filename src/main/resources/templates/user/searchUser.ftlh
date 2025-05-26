<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container-fluid" style="margin-left: 250px">
        <div class="row">
            <div class="col-12">
                <div class="input-group mb-3">
                    <form action="/user/search" method="get">
                        <input type="text" name="name" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <span class="input-group-text" id="basic-addon2">Search</span>
                    </form>
                </div>
            </div>
            <div class="col-12">
                <#list users as user>
                    <div class="card">
                        <div class="card-body">
                            <a href="/user/searched?name=${user.email}" class="card-title">${user.email}</a>
                        </div>
                    </div>
                </#list>
            </div>
        </div>

    </div>


</@main.layout>