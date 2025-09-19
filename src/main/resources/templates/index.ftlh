<#import "layout.ftlh" as main>
<@main.layout ; spring>

    <div class="row mx-auto" style="margin-left: 250px !important;">
        <#list files as file>
            <div class="col-12">
                <div class="card p-3  mb-3">
                                                    <h3 class="card-title text-info">${file.userDto.email}</h3>
                    <img src="/file/get/${file.fileName}" class="card-img-top post-img" alt="...">

                    <div class="row mt-3 ms-2">
                        <div class="col-1">
                            <form class="heard-form" data-fileName="${file.fileName}" action="/file/upload/like/${file.fileName}" method="post">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}"/>
                                </#if>
                                <button type="submit" class="btn-heart text-light btn btn-link p-0 "><i class="far fa-heart"></i></button>
                            </form>
                        </div>
                        <div class="col-1">
                            <button type="button" class="button-comment text-light btn btn-link p-0" data-id="${file.id}"><i class="far fa-comment"></i></button>
                        </div>


                    </div>

                    <div class="card-body text-light">
                        <p class="card-text like-count" data-fileName="${file.fileName}">${file.likes?size!"0"} - likes</p>
                        <p class="card-text">${file.description!""}</p>

                        <form class="comment-form" data-fileName="${file.fileName}" action="/file/upload/comment/${file.fileName}" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <div class="add-comment">
                                <@spring.formInput "commentDto.comment" 'id="form3Example1cg" class="form-control text-light comment-input mb-3 bg-dark" placeholder="Add comment..."' />
                                <@spring.showErrors "<br>" "error text-danger" />
                                <button type="submit" class="btn btn-light mb-2">Save comment</button>
                            </div>
                        </form>


                        <div data-id="${file.id}" class="comments-${file.id} d-none comments-card">
                            <#if file.comments??>
                                <#list file.comments as comment>
                                    <div class="card-comment">
                                        <p class="comment-author">${comment.userDto.email}</p>
                                        <p class="comment-text">${comment.comment}</p>
                                    </div>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>



    <script src="../static/js/main.js"> </script>


    <style>

        .post-img {
            height: 350px;
            object-fit: cover;
            width: 100%;
        }

        .container-fluid{
            background-color: #000;
            color: #fff;
        }

        .card{
            background-color: #000 !important;
            width: 75%;
        }
        .comment-input{
            width: 100%;
            background: transparent;
            border: none;;
            color: #fff;
            padding: 10px 0;
            backendground-color: black;
        }

        .comments {
            margin-top: 15px;
        }

        .card-comment {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #333;
            background-color: #1a1a1a;
            border-radius: 8px;
        }

        .comment-author {
            margin: 0;
            font-weight: bold;
            color: #65a5f2;
        }

        .comment-text {
            margin: 5px 0 0 0;
            color: #ddd;
        }

        .description{
            color: white;
        }

        .post-actions{
            color: white;
        }
        .feed {
            flex-grow: 1;
            display: block;
            justify-content: center;
            padding: 40px;
            background-color: #000;
            color: white;
        }
        .post {
            background-color: #121212;
            border: 1px solid #262626;
            border-radius: 8px;
            width: 50%;
            overflow: hidden;
            padding: 20px;
        }

        .post-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px;
        }

        .post-header img {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .post-image img {
            width: 100%;
            display: block;
        }

        .post-caption {
            padding: 10px;
            font-size: 14px;
            color: #ddd;
        }
    </style>

</@main.layout>