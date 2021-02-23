jest.mock('mysql2', () => {
    return {
        createPool: () => {
            return {
                promise: () => {
                    return {query: ''}
                }
            }
        }
    }
})

jest.mock('../config', () => {
    return {
        yxtr: {},
        yxtw: {},
        mallw: {},
        mallr: {},
        mqs:[],
        token:{}
    }
})




