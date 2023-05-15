def sol(arr):
    avg = sum(arr)/len(arr)
    ans = [x for x in arr if x <= avg]

    return len(ans)


for tc in range(int(input())):
    n = int(input())
    arr = list(map(int, input().split()))

    res = sol(arr)
    print(f'#{tc+1} {str(res)}')
