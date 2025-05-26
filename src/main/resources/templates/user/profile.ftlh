<#import "../layout.ftlh" as main>
<link rel="stylesheet" href="../../static/css/profile.css">
<@main.layout ; spring>
    <div class="container-fluid" style="margin-left: 250px">
        <div class="row my-5 ms-3">
            <div class="col col-12">
                <div class="row align-items-center mb-2">
                    <div class="col-2">
                        <#if userDto.getAvatar()??>
                            <img src="/file/get/${userDto.getAvatar().fileName}" alt="Profile" class="profile-avatar">
                        <#else>
                            <i class="fas fa-user-circle me-3 rounded" style="font-size: 80px; color: #6c757d;"></i>
                        </#if>
                    </div>
                    <div class="col-10 ">
                        <h2>${userDto.email}</h2>
                    </div>
                </div>

                    <div class="profile-info">
                        <div class="profile-stats">
                            <p class="me-3"><strong>${posts}</strong>-  posts</p>
                            <p class="me-3"><strong>${followers}</strong>-  followers</p>
                            <p class="me-3"><strong>${following}</strong>-  following</p>
                        </div>
                        <div class="profile-bio">
                            <p><strong>${userDto.firstName} ${userDto.lastName}</strong></p>
                            <p>${userDto.description!""}</p>
                        </div>
                    </div>
                <#if !isCurrent>
                    <button type="button" class="btn btn-primary">Follow</button>
                </#if>

            </div>

            <div class="text-between lead">
                <h2 class="text-center">Posts</h2>
                <hr>
            </div>

            <div class="col-12">
                <div class="row">
                    <#list files as file>
                        <div class="col-12 col-sm-6 col-md-4 mb-4">
                            <div class="card p-3 mb-3">
<#--                                <h5 class="card-title">${file.userDto.email}</h5>-->
                                <img src="/file/get/${file.fileName}" class="card-img-top post-img" alt="...">

                                <div class="row mt-3 ms-2">
                                    <div class="col-1">
                                        <form action="/file/upload/like/${file.fileName}" method="post">
                                            <#if _csrf??>
                                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                       value="${(_csrf.token)!'csrf-token'}"/>
                                            </#if>
                                            <button type="submit" class=" text-light btn btn-link p-0 "><i class="far fa-heart"></i></button>
                                        </form>
                                    </div>
                                    <div class="col-1">
                                        <button type="button" class="button-comment text-light btn btn-link p-0" data-id="${file.id}"><i class="far fa-comment"></i></button>
                                    </div>


                                </div>

                                <div class="card-body">
                                    <p class="card-text">${file.likes?size!"0"} - likes</p>
                                    <p class="card-text">${file.description!""}</p>

                                    <form action="/file/upload/comment/${file.fileName}" method="post">
                                        <#if _csrf??>
                                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                                   value="${(_csrf.token)!'csrf-token'}"/>
                                        </#if>
                                        <div class="add-comment">
                                            <@spring.formInput "commentDto.comment" 'id="form3Example1cg" class="form-control text-light comment-input mb-3 bg-dark" placeholder="Add comment..."' />
                                            <@spring.showErrors "<br>" "error text-danger" />
                                            <button type="submit" class="btn btn-light">Save comment</button>
                                        </div>
                                    </form>

                                    <div class="comments-${file.id} d-none" >
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
            </div>
        </div>
    </div>

    <script>
        window.onload = function () {
            document.querySelectorAll('.button-comment').forEach(button => {
                button.addEventListener('click', function () {
                    let id = this.getAttribute('data-id');
                    let comments = document.querySelector('.comments-' + id);

                    if(comments.classList.contains('d-none')){
                        comments.classList.remove('d-none');
                    }else{
                        comments.classList.add('d-none');
                    }
                })
            })
        }
    </script>




</@main.layout>

