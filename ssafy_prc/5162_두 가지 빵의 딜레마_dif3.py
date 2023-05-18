def sol(a_b, b_b, c):
    ans = 0
    if a_b > b_b:
        a_b, b_b = b_b, a_b

    ans += c//a_b
    c %= a_b

    ans += c // b_b

    return ans


for tc in range(int(input())):
    a, b, c = map(int, input().split())

    max_num = sol(a, b, c)

    print(f'#{tc+1} {max_num}')