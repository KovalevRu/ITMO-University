import re

def parse_json(input_json):
    """
    Преобразует строку JSON в объект Python, используя  правила грамматики.
    """
    def parse_value(value):
        value = value.strip()
        if value.startswith("{") and value.endswith("}"):
            return parse_object(value)  # Вложенный объект
        elif value.startswith("[") and value.endswith("]"):
            return parse_array(value)  # Массив
        elif value == "null":
            return None
        elif value == "true":
            return True
        elif value == "false":
            return False
        elif re.match(r'^-?\d+(\.\d+)?$', value):  # Числа
            return float(value) if '.' in value else int(value)
        elif value.startswith('"') and value.endswith('"'):  # Строки
            return value[1:-1]
        else:
            raise ValueError(f"Invalid value: {value}")

    def parse_object(json_str):
        obj = {}
        json_str = json_str.strip('{}').strip()
        if not json_str:
            return obj

        # Разделяем пары ключ-значение
        pairs = re.findall(r'"(.*?)"\s*:\s*(\[[^\]]*\]|\{[^\}]*\}|".*?"|true|false|null|-?\d+(\.\d+)?)(,|$)', json_str)
        for key, value, _, _ in pairs:
            obj[key] = parse_value(value)
        return obj

    def parse_array(json_str):
        json_str = json_str.strip('[]').strip()
        if not json_str:
            return []

        # Разделяем элементы массива
        elements = re.findall(r'(\{[^\}]*\}|\[[^\]]*\]|".*?"|true|false|null|-?\d+(\.\d+)?)(,|$)', json_str)
        return [parse_value(el[0].strip()) for el in elements]

    json_str = input_json.strip()
    if json_str.startswith('{'):
        return parse_object(json_str)
    elif json_str.startswith('['):
        return parse_array(json_str)
    else:
        raise ValueError("Invalid JSON input")


def json_to_xml(json_obj, root_tag="root"):
    """
    Преобразует JSON объект в XML строку.
    json_obj: Входной JSON объект (словарь или список).
    root_tag: Корневой тег XML.
    """
    def build_xml(obj, indent=0):
        xml_str = ""
        indentation = "  " * indent

        if isinstance(obj, dict):
            for key, value in obj.items():
                xml_str += f"{indentation}<{key}>\n"
                xml_str += build_xml(value, indent + 1)
                xml_str += f"{indentation}</{key}>\n"
        elif isinstance(obj, list):
            for item in obj:
                xml_str += f"{indentation}<item>\n"
                xml_str += build_xml(item, indent + 1)
                xml_str += f"{indentation}</item>\n"
        else:
            xml_str += f"{indentation}{str(obj)}\n"

        return xml_str

    xml_result = f"<{root_tag}>\n"
    xml_result += build_xml(json_obj, indent=1)
    xml_result += f"</{root_tag}>\n"

    return xml_result


def main():
    # Чтение JSON из файла input.json
    with open('input.json', 'r', encoding='utf-8') as file:
        input_json = file.read()

    json_data = parse_json(input_json)

    xml_output = json_to_xml(json_data)

    # Записываем результат в output.xml
    with open('output.xml', 'w', encoding='utf-8') as file:
        file.write(xml_output)


if __name__ == "__main__":
    main()