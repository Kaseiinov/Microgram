<#import "layout.ftlh" as main>
<@main.layout ; spring>

    <main class="feed">
        <#list files as file>
            <div class="post mb-3">
                <div class="post-header">
                    <h2>${file.userDto.email}</h2>
                </div>
                <div class="post-image">
                    <img src="/file/get/${file.fileName}" alt="Post image">
                </div>
                <div class="post-details">
                    <div class="post-actions">
                        <form action="/file/upload/like/${file.fileName}" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}"/>
                            </#if>
                            <button type="submit" ><i class="far fa-heart"></i></button>
                        </form>

                        <button type="button" class="button-comment"><i class="far fa-comment"></i></button>
                    </div>
                    <div class="likes">
                        <strong>${file.likes?size!"0"} likes</strong>
                    </div>
                    <div class="post-text">
                        <p class="description">${file.description!""}</p>
                    </div>
                    <form action="/file/upload/comment/${file.fileName}" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="add-comment">
                            <@spring.formInput "commentDto.comment" 'id="form3Example1cg" class="comment-input" placeholder="Add comment..."' />
                            <@spring.showErrors "<br>" "error text-danger" />
                            <button type="submit" class="btn btn-primary">Save comment</button>
                        </div>
                    </form>
                    <div class="comments">
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
        </#list>
    </main>

    <script>
        $(document).ready(() => {
            let comment = $('.comments').hide();

            $('.button-comment').on('click', () => {
                comment.toggle();
            });
        });
    </script>


    <style>
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