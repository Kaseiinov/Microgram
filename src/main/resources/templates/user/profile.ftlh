<#import "../layout.ftlh" as main>
<link rel="stylesheet" href="../../static/css/profile.css">
<@main.layout ; spring>
    <div class="profile-container mt-5 ms-5">
        <#if userDto.getAvatar()??>
            <img src="/file/get/${userDto.getAvatar().fileName}" alt="Profile" class="profile-avatar">
        <#else>
            <i class="fas fa-user-circle me-3 rounded" style="font-size: 80px; color: #6c757d;"></i>
        </#if>

        <div class="profile-info">
            <h2>${userDto.email}</h2>
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
    </div>
</@main.layout>

