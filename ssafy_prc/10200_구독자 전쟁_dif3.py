def sol(n, a, b):
    ans = []

    ans.append(min(a, b))

    if n >= a+b:
        ans.append(0)
    else:
        ans.append(a+b-n)

    return ans


for tc in range(int(input())):
    n, a, b = map(int, input().split())
    res = sol(n, a, b)
    print(f'#{tc+1} {res[0]} {res[1]}')