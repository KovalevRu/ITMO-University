#!/bin/bash
# Сама функция для поиска файлов
list_files() {
    local dir=$1
    local pattern=$2
    for entry in "$dir"/*; do
        if [[ -d "$entry" ]]; then
            # Рекурсивно обходим вложенные каталоги
            list_files "$entry" "$pattern"
        elif [[ -f "$entry" && "$(basename "$entry")" == $pattern ]]; then
            # Если это файл и подходит по шаблону то вывод  к пайпу 
            echo "$entry"
        fi
    done
}



dir=$1
pattern=$2

# Проверка на есть ли директория
if [[ ! -d "$dir" ]]; then
    echo "Ошибка: директория '$dir' не существует."
    exit 0
fi

# Основной вызов
list_files "$dir" "$pattern" | xargs -I {} ls -ltu {} 2>/dev/null | head -n 4



#  *on*
# ^*on*