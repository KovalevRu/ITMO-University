def parse_input_file():
    input_path = "input.json"
    output_path = "output.xml"

    with open(input_path, "r", encoding="utf-8") as file:
        json_string = file.read().strip()

    def json_to_xml(json_obj, root_tag, indent=0):
        """Конвертирует JSON в строку XML с отступами."""
        indentation = "  " * indent
        xml_str = f"{indentation}<{root_tag}>"

        if isinstance(json_obj, dict):
            xml_str += "\n"
            for key, value in json_obj.items():
                # Рекурсивно обрабатываем вложенные структуры
                xml_str += json_to_xml(value, key, indent + 1)
            xml_str += f"{indentation}</{root_tag}>\n"
        elif isinstance(json_obj, list):
            xml_str += "\n"
            for item in json_obj:
                xml_str += json_to_xml(item, root_tag, indent + 1)
            xml_str += f"{indentation}</{root_tag}>\n"
        else:
            # Если это простое значение (не словарь или список)
            xml_str += f"{json_obj}</{root_tag}>\n"

        return xml_str

    # Преобразуем строку JSON в объект (словарь)
    json_data = eval(json_string)

    # Берем первый ключ JSON для корня XML
    root_key = next(iter(json_data))  # Получаем первый ключ из JSON, чтобы использовать его как корень

    xml_content = json_to_xml(json_data[root_key], root_key)

    # Запись результата в файл
    with open(output_path, "w", encoding="utf-8") as output_file:
        output_file.write(xml_content)

def main():
    parse_input_file()

if __name__ == "__main__":
    main()