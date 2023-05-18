def sol(str_arr, max_len):
    ans = ""
    for i in range(5):
        if len(str_arr[i]) < max_len:
            for j in range(max_len - len(str_arr[i])):
                str_arr[i].append("")

    for i in range(max_len):
        for j in range(5):
            ans += arr[j][i]

    return ans


for tc in range(int(input())):
    arr = [list(map(str, input())) for _ in range(5)]
    max_len = 0
    for i in range(len(arr)):
        if max_len < len(arr[i]):
            max_len = len(arr[i])

    res = sol(arr, max_len)
    print(f'#{tc+1} {res}')
