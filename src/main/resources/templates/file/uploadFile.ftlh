<#import "../layout.ftlh" as main>

<@main.layout ; spring>

    <style>

        h2 {
            color: red;
            text-align: center;
            margin-bottom: 30px;
        }
    </style>

    <h2>Публикация</h2>

    <form action="/file/upload" method="post" enctype="multipart/form-data">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <input type="hidden" name="userDto.email" value="${user}">

        <@spring.formInput "fileDto.description" 'id="form3Example1cg" class="form-inputs" placeholder="Description"' />
        <@spring.showErrors "<br>" "error text-danger" />

        <div class="mb-3">
            <label for="formFileImage" class="form-label">Загрузить файл изображения</label>
            <input name="multipartFile" class="form-control" type="file" id="formFileImage" accept="image/*">

            <@spring.bind "fileDto.multipartFile" />
            <#if spring.status.error>
                <div class="error text-danger">
                    <#list spring.status.errorMessages as error>
                        ${error}<br>
                    </#list>
                </div>
            </#if>
        </div>


        <button type="submit" class="btn btn-success w-100 mb-3">Сохранить</button>
    </form>

</@main.layout>
