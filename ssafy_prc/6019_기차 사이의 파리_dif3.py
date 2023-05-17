for tc in range(int(input())):
    d, a, b, f = map(int, input().split())
    time = d/(a+b)
    print(f'#{tc+1} {round(time*f, 6)}')