<#import "layout.ftlh" as main>
<@main.layout ; spring>

    <main class="feed">
        <div class="post">
            <div class="post-header">
                <img src="https://via.placeholder.com/32" class="profile-pic">
            </div>
            <div class="post-image">
                <img src="image.png" alt="Post image">
            </div>
            <div class="post-details">
                <div class="post-actions">
                    <i class="far fa-heart"></i>
                    <i class="far fa-comment"></i>
                </div>
                <div class="likes">
                    <strong>3,324 likes</strong>
                </div>
                <div class="post-text">
                    <p class="description">description</p>
                </div>
                <div class="add-comment">
                    <input type="text" placeholder="Add a comment..." style="width: 100%; background: transparent; border: none; color: #fff; padding: 10px 0;">
                </div>
            </div>
        </div>
    </main>

    <style>
        .feed {
            flex-grow: 1;
            display: flex;
            justify-content: center;
            padding: 40px;
            background-color: #000;
        }
        .post {
            background-color: #121212;
            border: 1px solid #262626;
            border-radius: 8px;
            width: 75%;
            overflow: hidden;
            padding: 10px;
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