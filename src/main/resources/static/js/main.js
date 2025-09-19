window.onload = function () {
    likes();
    document.querySelectorAll('.button-comment').forEach(button => {
        button.addEventListener('click', function () {
            let id = this.getAttribute('data-id');
            let comments = document.querySelector('.comments-' + id);
            comments.classList.toggle('d-none');
        });
    });

    function likes(){
        document.querySelectorAll('.heard-form').forEach(form => {
            form.addEventListener('submit', async function (e) {
                e.preventDefault();

                const fileName = form.getAttribute('data-fileName');
                const csrfInput = form.querySelector('input[name=_csrf]');

                await fetch(`/file/upload/like/` + fileName, {
                    method: 'POST',
                    headers: {
                        'X-CSRF-TOKEN': csrfInput.value
                    }
                });

                await updateLikes(fileName);
            });
        });

        async function updateLikes(fileName) {
            const response = await fetch(`/file/like/count?fileName=${fileName}`);
            const data = await response.json();

            const likeElement = document.querySelector(`.like-count[data-fileName="${fileName}"]`);
            if (likeElement) {
                likeElement.textContent = `${data.likes} - likes`;
            }
        }
    }

    comments();

    function comments(){
        document.querySelectorAll('.comment-form').forEach(form => {
            form.addEventListener('submit', async function (e) {
                e.preventDefault();
                const fileName = form.getAttribute('data-fileName');
                const csrfInput = form.querySelector('input[name=_csrf]');
                const commentInput = form.querySelector('[name="comment"]');
                const comment = commentInput.value.trim();

                console.log(comment)
                if(comment === "") return

                const formData = new FormData();
                formData.append("comment", comment);

                const response = await fetch(`/file/upload/comment/${fileName}`, {
                    method: 'POST',
                    headers: {
                        'X-CSRF-TOKEN': csrfInput.value
                    },
                    body: formData
                });
                if (response.ok) {
                    commentInput.value = "";
                    console.log("Комментарий добавлен!");
                } else {
                    console.log("Ошибка при добавлении комментария");
                }

                await getComments(fileName)
            })
        })
        async function getComments(fileName) {
            try {
                const response = await fetch(`/file/getComments?fileName=${fileName}`);
                const data = await response.json();

                // 1. Находим ВСЕ контейнеры комментариев
                const allCommentsContainers = document.querySelectorAll('[data-id]');

                // 2. Ищем нужный контейнер по fileName
                let targetContainer = null;

                allCommentsContainers.forEach(container => {
                    // Проверяем, содержит ли контейнер изображение с нужным fileName
                    const img = container.closest('.card').querySelector('img');
                    if (img && img.src.includes(fileName)) {
                        targetContainer = container;
                    }
                });

                if (!targetContainer) {
                    throw new Error('Не найден контейнер комментариев для файла: ' + fileName);
                }

                // 3. Получаем fileId из data-id контейнера
                const fileId = targetContainer.getAttribute('data-id');

                // 4. Очищаем и показываем контейнер
                targetContainer.innerHTML = '';
                targetContainer.classList.remove('d-none');

                // 5. Добавляем комментарии
                if (data.comments && data.comments.length > 0) {
                    data.comments.forEach(comment => {
                        targetContainer.innerHTML += `
                    <div class="card-comment">
                        <p class="comment-author">${comment.email || 'Аноним'}</p>
                        <p class="comment-text">${comment.comment}</p>
                    </div>
                `;
                    });
                } else {
                    targetContainer.innerHTML = '<p>Комментариев пока нет</p>';
                }

            } catch (error) {
                console.error('Ошибка загрузки комментариев:', error);
                // Можно добавить уведомление об ошибке в интерфейс
            }
        }
    }
}