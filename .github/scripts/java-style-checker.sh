#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

check_file() {
    local file=$1
    local errors=0
    local line_number=0

    echo -e "\n${YELLOW}Analyzing $file${NC}"

    while IFS= read -r line; do
        ((line_number++))

        # Check indentation of 4 spaces
        if [[ "$line" =~ ^[[:space:]] ]]; then
            indent_count=$(echo "$line" | awk -F'[^ ]' '{print length($1)}')
            if ((indent_count % 4 != 0)); then
                echo -e "${RED}Line $line_number: The indentation is not a multiple of 4 spaces${NC}"
                ((errors++))
            fi
        fi

        # Check spaces around operators
        if [[ "$line" =~ [^[:space:]][\+\-\*\/\=][^[:space:]] ]]; then
            echo -e "${RED}Line $line_number: Missing space separator around operator${NC}"
            ((errors++))
        fi

        # Check space before curl bracket
        if [[ "$line" =~ [^[:space:]]\{ ]]; then
            echo -e "${RED}Line $line_number: Missing space before curl bracket '{'${NC}"
            ((errors++))
        fi

        # Check maximum length of a line (120)
        if [ ${#line} -gt 120 ]; then
            echo -e "${RED}Line $line_number: Exceeds 120 characters${NC}"
            ((errors++))
        fi

        # Check spaces at the end of line
        if [[ "$line" =~ [[:space:]]$ ]]; then
            echo -e "${RED}Line $line_number: Spaces at the end of line${NC}"
            ((errors++))
        fi

    done < "$file"

    if [ $errors -eq 0 ]; then
        echo -e "${GREEN}The file $file passes all the style requirements!${NC}"
    else
        echo -e "${RED}There were $errors style errors $file${NC}"
    fi

    return $errors
}

# Verify if there is a directory provided
if [ $# -eq 0 ]; then
    search_dir="."
else
    search_dir="$1"
fi

echo "Searching Java files in $search_dir/"

total_errors=0
total_files=0
files_with_errors=0

while IFS= read -r -d '' file; do
    ((total_files++))
    check_file "$file"
    file_errors=$?

    if [ $file_errors -gt 0 ]; then
        ((files_with_errors++))
        ((total_errors += file_errors))
    fi
done < <(find "$search_dir" -type f -name "*.java" -print0)

echo -e "\n${YELLOW}=== Checkstyle Summary ===${NC}"
echo "Files analyzed: $total_files"
echo "Files with errors: $files_with_errors"
echo "Number of errors found: $total_errors"

if [ $total_errors -eq 0 ]; then
    echo -e "${GREEN}All files match the style!${NC}"
else
    echo -e "${RED}There are files that need to fix their style${NC}"
fi

exit $((files_with_errors > 0))