def sol(n):
    for i in range(1, int(n)+1):
        val = i**3
        if val == n:
            return i
        elif val > n:
            return -1


for tc in range(int(input())):
    n = int(input())
    res = sol(n)
    print(f'#{tc+1} {res}')