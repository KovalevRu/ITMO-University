import re
def parse_input_file():
    input_path = "input.json"
    output_path = "output.xml"

    # Чтение JSON строки из файла
    with open(input_path, "r", encoding="utf-8") as file:
        json_string = file.read().strip()

    # Используем регулярное выражение для извлечения ключей и значений
    def parse_json(json_string):
        pattern = r'"(.*?)"\s*:\s*("(.*?)"|\d+|true|false|null|\{.*?\}|\[.*?\])'
        matches = re.findall(pattern, json_string)

        obj = {}
        for match in matches:
            key = match[0]
            value = match[1]

            # Обработка значений, которые являются строками, числами, булевыми значениями, объектами или массивами
            if value.startswith('"') and value.endswith('"'):
                obj[key] = value[1:-1]  # Убираем кавычки вокруг строки
            elif value.isdigit():
                obj[key] = int(value)  # Число
            elif value.lower() == "true":
                obj[key] = True
            elif value.lower() == "false":
                obj[key] = False
            elif value.lower() == "null":
                obj[key] = None
            elif value.startswith("{") and value.endswith("}"):
                obj[key] = parse_json(value)  # Рекурсивно обрабатываем вложенные объекты
            elif value.startswith("[") and value.endswith("]"):
                obj[key] = parse_json(value)  # Рекурсивно обрабатываем массивы
            else:
                obj[key] = value  # Прочее

        return obj

    # Преобразование JSON строки в объект
    json_data = parse_json(json_string)

    def json_to_xml(json_obj, root_tag, indent=0):
        """Конвертирует JSON в строку XML с отступами."""
        indentation = "  " * indent
        xml_str = f"{indentation}<{root_tag}>"

        if isinstance(json_obj, dict):
            xml_str += "\n"
            for key, value in json_obj.items():
                xml_str += json_to_xml(value, key, indent + 1)
            xml_str += f"{indentation}</{root_tag}>\n"
        elif isinstance(json_obj, list):
            xml_str += "\n"
            for item in json_obj:
                xml_str += json_to_xml(item, root_tag, indent + 1)
            xml_str += f"{indentation}</{root_tag}>\n"
        else:
            xml_str += f"{json_obj}</{root_tag}>\n"

        return xml_str

    root_key = next(iter(json_data))  # Получаем первый ключ из JSON, чтобы использовать его как корень

    xml_content = json_to_xml(json_data[root_key], root_key)

    with open(output_path, "w", encoding="utf-8") as output_file:
        output_file.write(xml_content)

def main():
    parse_input_file()

if __name__ == "__main__":
    main()