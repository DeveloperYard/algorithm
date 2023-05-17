for tc in range(int(input())):
    n = int(input())
    avg = 0
    for i in range(n):
        p, x = map(str, input().split())
        avg += round(float(p), 6)*int(x)

    print(f'#{tc+1} {avg}')