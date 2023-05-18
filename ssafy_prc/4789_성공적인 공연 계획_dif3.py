def sol(peoples):
    ans = 0
    cur_p = int(peoples[0])
    print(peoples)
    for i in range(1, len(peoples)):
        print(cur_p)
        if peoples[i] == 0:
            continue
        if cur_p < i:
            # print(f'i: {i}    cur: {cur_p}')
            ans += (i-cur_p)
            cur_p += (i-cur_p)
            cur_p += int(peoples[i])
        else:
            cur_p += int(peoples[i])

    return ans


for tc in range(int(input())):
    s = list(input())
    res = sol(s)
    print(f'#{tc+1} {res}')