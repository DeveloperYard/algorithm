def sol(m, n):
    ans = 0

    compareBin = 0
    for i in range(m):
        compareBin += 2**i

    if compareBin & n == compareBin:
        return True
    else:
        return False


for tc in range(int(input())):
    m, n = map(int, input().split())
    res = "ON" if sol(m, n) else "OFF"
    print(f'#{tc} {res}')
