for tc in range(int(input())):
    n, k = map(int, input().split())
    compArr = [0]*n
    arr = list(map(int, input().split()))
    for i in arr:
        compArr[i-1] = 1

    print(f'#{tc+1}', end=' ')
    for i in range(len(compArr)):
        if compArr[i] == 0:
            print(f'{i+1}', end=' ')
    print()
